import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoUpdateComponent } from 'app/entities/invito/invito-update.component';
import { InvitoService } from 'app/entities/invito/invito.service';
import { Invito } from 'app/shared/model/invito.model';

describe('Component Tests', () => {
  describe('Invito Management Update Component', () => {
    let comp: InvitoUpdateComponent;
    let fixture: ComponentFixture<InvitoUpdateComponent>;
    let service: InvitoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InvitoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Invito(123);
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
        const entity = new Invito();
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
