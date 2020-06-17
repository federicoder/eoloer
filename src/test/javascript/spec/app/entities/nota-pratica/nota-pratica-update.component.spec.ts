import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { NotaPraticaUpdateComponent } from 'app/entities/nota-pratica/nota-pratica-update.component';
import { NotaPraticaService } from 'app/entities/nota-pratica/nota-pratica.service';
import { NotaPratica } from 'app/shared/model/nota-pratica.model';

describe('Component Tests', () => {
  describe('NotaPratica Management Update Component', () => {
    let comp: NotaPraticaUpdateComponent;
    let fixture: ComponentFixture<NotaPraticaUpdateComponent>;
    let service: NotaPraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotaPraticaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NotaPraticaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotaPraticaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotaPraticaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotaPratica(123);
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
        const entity = new NotaPratica();
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
