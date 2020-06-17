import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TemplatePraticaUpdateComponent } from 'app/entities/template-pratica/template-pratica-update.component';
import { TemplatePraticaService } from 'app/entities/template-pratica/template-pratica.service';
import { TemplatePratica } from 'app/shared/model/template-pratica.model';

describe('Component Tests', () => {
  describe('TemplatePratica Management Update Component', () => {
    let comp: TemplatePraticaUpdateComponent;
    let fixture: ComponentFixture<TemplatePraticaUpdateComponent>;
    let service: TemplatePraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TemplatePraticaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TemplatePraticaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TemplatePraticaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TemplatePraticaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TemplatePratica(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TemplatePratica();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
