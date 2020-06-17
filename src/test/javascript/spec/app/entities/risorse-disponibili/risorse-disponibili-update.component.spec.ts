import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { RisorseDisponibiliUpdateComponent } from 'app/entities/risorse-disponibili/risorse-disponibili-update.component';
import { RisorseDisponibiliService } from 'app/entities/risorse-disponibili/risorse-disponibili.service';
import { RisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';

describe('Component Tests', () => {
  describe('RisorseDisponibili Management Update Component', () => {
    let comp: RisorseDisponibiliUpdateComponent;
    let fixture: ComponentFixture<RisorseDisponibiliUpdateComponent>;
    let service: RisorseDisponibiliService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RisorseDisponibiliUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RisorseDisponibiliUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RisorseDisponibiliUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RisorseDisponibiliService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RisorseDisponibili(123);
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
        const entity = new RisorseDisponibili();
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
